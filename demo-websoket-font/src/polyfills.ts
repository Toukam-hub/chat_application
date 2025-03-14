(window as any).global = window;
import * as process from "process";
import { Buffer as BufferPolyfill } from "buffer";

(window as any).process = process;
(window as any).Buffer = BufferPolyfill;
